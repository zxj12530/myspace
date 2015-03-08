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
 * Base基本的几乎适应全部需求的上传工具类
 * 
 * @param request
 *            用户的request请求
 * @param Encode
 *            指定编码如果为NULL，或者空字符串，则默认使用UTF-8
 * @param path
 *            设置文件路径
 * @param filename
 *            文件名，如果为NULL或者为空字符串，就使用上传时的文件名
 * @param CatchPath
 *            缓存临时路径，如果为NULL或者为空字符串，缓存目录就是文件保存目录
 * @param FileTypes
 *            文件类型，如果为NULL或者为空字符串数组，类型无要求， 如果设置了类型数组，只要与其中一个类型匹配正确，
 *            就保存文件。如果没有匹配到文件类型，就抛出异常（文件类型未能匹配）
 * @param FileMaxSize
 *            文件最大值，如果 <=0，就是对文件大小无限制 有数值的话，如果文件大小大于设置的最大值，就会就抛出异常（文件大小超出设置的最大值）
 * @return Map<String, String> reques携带的参数数据
 */
public class FileUpLoad
{
	/**
	 * 负责处理上传的方法 文件名则是使用上传时的文件名
	 * 
	 * @param request
	 *            用户的request
	 * @param Encode
	 *            指定编码如果为NULL，或者空字符串，则默认使用UTF-8
	 * @param path
	 *            指定保存路径
	 * @return Map<String, String> reques携带的参数数据
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path)
	{
		return BaseFileUpload(request, Encode, path, null, null, null, 0);
	}

	/**
	 * 负责处理上传的方法 文件名则是使用上传时的文件名
	 * 
	 * @param request
	 *            用户的request
	 * @param path
	 *            指定保存路径
	 * @return Map<String, String> reques携带的参数数据
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String path)
	{
		return BaseFileUpload(request, null, path, null, null, null, 0);
	}

	/**
	 * 负责处理上传的方法
	 * 
	 * @param request
	 *            用户的request
	 * @param Encode
	 *            指定编码如果为NULL，或者空字符串，则默认使用UTF-8
	 * @param path
	 *            指定保存路径
	 * @param filename
	 *            指定保存保存文件名
	 * @return Map<String, String> reques携带的参数数据
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path, String filename)
	{
		return BaseFileUpload(request, Encode, path, filename, null, null, 0);
	}

	/**
	 * 负责处理上传的方法
	 * 
	 * @param request
	 *            用户的request
	 * @param Encode
	 *            指定编码如果为NULL，或者空字符串，则默认使用UTF-8
	 * @param path
	 *            指定保存路径
	 * @param filename
	 *            指定保存保存文件名
	 * @param CatchPath
	 *            指定临时存储目录
	 * @return Map<String, String> reques携带的参数数据
	 */
	public static Map<String, String> FileUpload(HttpServletRequest request,
			String Encode, String path, String filename, String CatchPath)
	{
		return BaseFileUpload(request, Encode, path, filename, CatchPath, null,
				0);
	}

	/**
	 * Base基本的几乎适应全部需求的上传工具类
	 * 
	 * @param request
	 *            用户的request请求
	 * @param Encode
	 *            指定编码，如果为NULL或者为空字符串，则默认使用UTF-8
	 * @param path
	 *            设置文件路径
	 * @param filename
	 *            文件名，如果为NULL或者为空字符串，就使用上传时的文件名
	 * @param CatchPath
	 *            缓存临时路径，如果为NULL或者为空字符串，缓存目录就是文件保存目录
	 * @param FileTypes
	 *            文件类型，如果为NULL或者为空字符串数组，类型无要求， 如果设置了类型数组，只要与其中一个类型匹配正确，
	 *            就保存文件。如果没有匹配到文件类型，就抛出异常（文件类型未能匹配）
	 * @param FileMaxSize
	 *            文件最大值，如果 <=0，就是对文件大小无限制
	 *            有数值的话，如果文件大小大于设置的最大值，就会就抛出异常（文件大小超出设置的最大值）
	 * @return Map<String, String> reques携带的参数数据
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
				request.setCharacterEncoding("utf-8"); // 设置编码
			} else
			{
				request.setCharacterEncoding(Encode); // 设置编码
			}
			// 获得磁盘文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
			// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
			/**
			 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以
			 * .tem 格式的 然后再将其真正写到 对应目录的硬盘上
			 */
			factory.setRepository(new File(path));
			if (!StringUtil.isEmpty(CatchPath))
			{
				factory.setRepository(new File(CatchPath));
			}
			// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
			factory.setSizeThreshold(100 * 1024);
			// 高水平的API文件上传处理
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 可以上传多个文件
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list)
			{
				// 获取表单的属性名字
				String name = item.getFieldName();
				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField())
				{
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString();
					// request.setAttribute(name, value);
					map.put(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else
				{
					if (item != null && item.getSize() > 0)
					{
						/**
						 * 以下三步，主要获取 上传文件的名字
						 */
						// 获取路径名
						// 如果没有指定文件名的话，就使用上传时的文件名
						// 如果有的话，就不作任何操作
						if (ObjectUtil.isNull(filename) || StringUtil.isEmpty(filename) || list.indexOf(item)>0)
						{
							String value = item.getName();
							// 索引到最后一个反斜杠
							int start = value.lastIndexOf("\\");
							// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
							filename = value.substring(start + 1);
						}
						// 如果文件类型不是空的，意思就是对文件类型有要求
						if (!StringUtil.allEmpty(FileTypes))
						{
							boolean flag = false;
							// 遍历文件类型数组
							for (String filetype : FileTypes)
							{
								// 判断文件是否符合要求
								if (filename.endsWith(filetype))
								{
									flag = true;
									break;
								}
							}
							// 如果没有匹配到类型的话，就跳过这个文件
							if (!flag)
							{
								throw new Exception("文件类型未能匹配");
								// continue;
							}
						}
						map.put(name, filename);
						// 判断文件大小
						System.out.println("文件的大小：" + item.getSize());
						// 如果发现FileMaxSize不为空，或者并且设置大于0
						// 就对文件大小进行比较
						if (FileMaxSize > 0)
						{
							// 再判断是否超出设置的最大值
							if (item.getSize() > FileMaxSize)
							{
								// continue;
								throw new Exception("文件大小超过最大值");
							}
						}

						// 真正写到磁盘上
						OutputStream out = new FileOutputStream(new File(
								new File(path), filename));
						InputStream in = item.getInputStream();
						int length = 0;
						byte[] buf = new byte[1024 * 1024];

						// in.read(buf) 每次读到的数据存放在 buf 数组中
						while ((length = in.read(buf)) != -1)
						{
							// 在 buf 数组中 取出数据 写到 （输出流）磁盘上
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
	 *            此方法负责清理删除指定文件夹的以tmp为后缀的临时文件
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
