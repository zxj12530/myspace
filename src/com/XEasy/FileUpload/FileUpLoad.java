package com.XEasy.FileUpload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.XEasy.Util.ObjectUtil;
import com.XEasy.Util.StringUtil;

/**
 * Base�����ļ�����Ӧȫ��������ϴ�������
 * 
 * @param request
 *            �û���request����
 * @param Encode
 *            ָ���������ΪNULL�����߿��ַ�������Ĭ��ʹ��UTF-8
 * @param path
 *            �����ļ�·��
 * @param filename
 *            �ļ��������ΪNULL����Ϊ���ַ�������ʹ���ϴ�ʱ���ļ���
 * @param CatchPath
 *            ������ʱ·�������ΪNULL����Ϊ���ַ���������Ŀ¼�����ļ�����Ŀ¼
 * @param FileTypes
 *            �ļ����ͣ����ΪNULL����Ϊ���ַ������飬������Ҫ�� ����������������飬ֻҪ������һ������ƥ����ȷ��
 *            �ͱ����ļ������û��ƥ�䵽�ļ����ͣ����׳��쳣���ļ�����δ��ƥ�䣩
 * @param FileMaxSize
 *            �ļ����ֵ����� <=0�����Ƕ��ļ���С������ ����ֵ�Ļ�������ļ���С�������õ����ֵ���ͻ���׳��쳣���ļ���С�������õ����ֵ��
 * @return Map<String, String> requesЯ���Ĳ�������
 */
public class FileUpLoad
{
	/**
	 * �������ϴ��ķ��� �ļ�������ʹ���ϴ�ʱ���ļ���
	 * 
	 * @param request
	 *            �û���request
	 * @param Encode
	 *            ָ���������ΪNULL�����߿��ַ�������Ĭ��ʹ��UTF-8
	 * @param path
	 *            ָ������·��
	 * @return Map<String, String> requesЯ���Ĳ�������
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path)
	{
		return BaseFileUpload(request, Encode, path, null, null, null, 0);
	}

	/**
	 * �������ϴ��ķ��� �ļ�������ʹ���ϴ�ʱ���ļ���
	 * 
	 * @param request
	 *            �û���request
	 * @param path
	 *            ָ������·��
	 * @return Map<String, String> requesЯ���Ĳ�������
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String path)
	{
		return BaseFileUpload(request, null, path, null, null, null, 0);
	}

	/**
	 * �������ϴ��ķ���
	 * 
	 * @param request
	 *            �û���request
	 * @param Encode
	 *            ָ���������ΪNULL�����߿��ַ�������Ĭ��ʹ��UTF-8
	 * @param path
	 *            ָ������·��
	 * @param filename
	 *            ָ�����汣���ļ���
	 * @return Map<String, String> requesЯ���Ĳ�������
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path, String filename)
	{
		return BaseFileUpload(request, Encode, path, filename, null, null, 0);
	}

	/**
	 * �������ϴ��ķ���
	 * 
	 * @param request
	 *            �û���request
	 * @param Encode
	 *            ָ���������ΪNULL�����߿��ַ�������Ĭ��ʹ��UTF-8
	 * @param path
	 *            ָ������·��
	 * @param filename
	 *            ָ�����汣���ļ���
	 * @param CatchPath
	 *            ָ����ʱ�洢Ŀ¼
	 * @return Map<String, String> requesЯ���Ĳ�������
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path, String filename, String CatchPath)
	{
		return BaseFileUpload(request, Encode, path, filename, CatchPath, null,
				0);
	}

	/**
	 * Base�����ļ�����Ӧȫ��������ϴ�������
	 * 
	 * @param request
	 *            �û���request����
	 * @param Encode
	 *            ָ�����룬���ΪNULL����Ϊ���ַ�������Ĭ��ʹ��UTF-8
	 * @param path
	 *            �����ļ�·��
	 * @param filename
	 *            �ļ��������ΪNULL����Ϊ���ַ�������ʹ���ϴ�ʱ���ļ���
	 * @param CatchPath
	 *            ������ʱ·�������ΪNULL����Ϊ���ַ���������Ŀ¼�����ļ�����Ŀ¼
	 * @param FileTypes
	 *            �ļ����ͣ����ΪNULL����Ϊ���ַ������飬������Ҫ�� ����������������飬ֻҪ������һ������ƥ����ȷ��
	 *            �ͱ����ļ������û��ƥ�䵽�ļ����ͣ����׳��쳣���ļ�����δ��ƥ�䣩
	 * @param FileMaxSize
	 *            �ļ����ֵ����� <=0�����Ƕ��ļ���С������
	 *            ����ֵ�Ļ�������ļ���С�������õ����ֵ���ͻ���׳��쳣���ļ���С�������õ����ֵ��
	 * @return Map<String, String> requesЯ���Ĳ�������
	 */
	public static Map<String, String> BaseFileUpload(
			HttpServletRequest request, String Encode, String path,
			String filename, String CatchPath, String[] FileTypes,
			int FileMaxSize)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			if (StringUtil.isEmpty(Encode))
			{
				request.setCharacterEncoding("utf-8"); // ���ñ���
			} else
			{
				request.setCharacterEncoding(Encode); // ���ñ���
			}
			// ��ô����ļ���Ŀ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
			// ������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
			/**
			 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������
			 * .tem ��ʽ�� Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
			 */
			factory.setRepository(new File(path));
			if (!StringUtil.isEmpty(CatchPath))
			{
				factory.setRepository(new File(CatchPath));
			}
			// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
			factory.setSizeThreshold(100 * 1024);
			// ��ˮƽ��API�ļ��ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �����ϴ�����ļ�
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list)
			{
				// ��ȡ������������
				String name = item.getFieldName();
				// �����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if (item.isFormField())
				{
					// ��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = item.getString();
					// request.setAttribute(name, value);
					map.put(name, value);
				}
				// �Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else
				{
					if (item != null && item.getSize() > 0)
					{
						/**
						 * ������������Ҫ��ȡ �ϴ��ļ�������
						 */
						// ��ȡ·����
						// ���û��ָ���ļ����Ļ�����ʹ���ϴ�ʱ���ļ���
						// ����еĻ����Ͳ����κβ���
						if (ObjectUtil.isNull(filename) || StringUtil.isEmpty(filename) || list.indexOf(item)>0)
						{
							String value = item.getName();
							// ���������һ����б��
							int start = value.lastIndexOf("\\");
							// ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
							filename = value.substring(start + 1);
						}
						// ����ļ����Ͳ��ǿյģ���˼���Ƕ��ļ�������Ҫ��
						if (!StringUtil.allEmpty(FileTypes))
						{
							boolean flag = false;
							// �����ļ���������
							for (String filetype : FileTypes)
							{
								// �ж��ļ��Ƿ����Ҫ��
								if (filename.endsWith(filetype))
								{
									flag = true;
									break;
								}
							}
							// ���û��ƥ�䵽���͵Ļ�������������ļ�
							if (!flag)
							{
								throw new Exception("�ļ�����δ��ƥ��");
								// continue;
							}
						}
						map.put(name, filename);
						// �ж��ļ���С
						System.out.println("�ļ��Ĵ�С��" + item.getSize());
						// �������FileMaxSize��Ϊ�գ����߲������ô���0
						// �Ͷ��ļ���С���бȽ�
						if (FileMaxSize > 0)
						{
							// ���ж��Ƿ񳬳����õ����ֵ
							if (item.getSize() > FileMaxSize)
							{
								// continue;
								throw new Exception("�ļ���С�������ֵ");
							}
						}

						// ����д��������
						OutputStream out = new FileOutputStream(new File(
								new File(path), filename));
						InputStream in = item.getInputStream();
						int length = 0;
						byte[] buf = new byte[1024 * 1024];

						// in.read(buf) ÿ�ζ��������ݴ���� buf ������
						while ((length = in.read(buf)) != -1)
						{
							// �� buf ������ ȡ������ д�� ���������������
							out.write(buf, 0, length);
						}
						in.close();
						out.close();
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (StringUtil.isEmpty(CatchPath))
			{
				clearTemp(path);
			} else
			{
				clearTemp(CatchPath);
			}
		}
		return map;
	}

	/**
	 * @param path
	 *            �˷�����������ɾ��ָ���ļ��е���tmpΪ��׺����ʱ�ļ�
	 */
	private static void clearTemp(String path)
	{
		File temps = new File(path);
		File temp[] = temps.listFiles();
		for (File tem : temp)
		{
			if (tem.isFile())
			{
				if (tem.getName().endsWith("tmp"))
				{
					tem.delete();
				}
			}
		}
	}
}
