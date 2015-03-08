package com.XEasy.Controller.Util;

public class PageTurn
{
	/**
	 * �Զ��ؽ������������ݽ��з��ࡣ��������ת����ֻ��һ������
	 * @param pramerType	Ҫת��������
	 * @param pars			��Ҫת��������
	 * @return				����һ��Object
	 */
	public static Object[] StringGetObj(String pramerType,String pars[])
	{
		//�������ж�һ���ǲ����ַ���
		if (StaticString.String.equals(pramerType))
		{
			return new Object[]{pars[0]};
		}
		//ת����Int
		if (StaticString.Int.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				int temp = 0;
				try
				{
					temp = Integer.parseInt(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Int));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Int));
				return null;
			}
		}
		//ת����double
		if (StaticString.Double.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				double temp = 0;
				try
				{
					temp = Double.parseDouble(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Double));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Double));
				return null;
			}
		}
		//ת����float
		if (StaticString.Float.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				float temp = 0;
				try
				{
					temp = Float.parseFloat(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Float));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Float));
				return null;
			}
		}
		//ת����char
		if (StaticString.Char.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				char temp = 0;
				try
				{
					temp = pars[0].toCharArray()[0];
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Char));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Char));
				return null;
			}
		}
		//ת����boolean
		if (StaticString.Boolean.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				if (StaticString.True.equals(pars[0]) || StaticString.False.equals(pars[0]))
				{
					return new Object[]{Boolean.parseBoolean(pars[0])};
				}else 
				{
					System.err.println(DataTurnException(pars[0], StaticString.Boolean));//��������ת���쳣
				}
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Boolean));
				return null;
			}
		}
		//ת����long����
		if (StaticString.Long.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				long temp = 0;
				try
				{
					temp = Long.parseLong(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Long));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Long));
				return null;
			}
		}
		//ת����short
		if (StaticString.Short.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				short temp = 0;
				try
				{
					temp = Short.parseShort(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Short));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Short));
				return null;
			}
		}
		//ת����byte
		if (StaticString.Byte.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				byte temp = 0;
				try
				{
					temp = Byte.parseByte(pars[0]);
				} catch (Exception e)
				{
					System.err.println(DataTurnException(pars[0], StaticString.Byte));//��������ת���쳣
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Byte));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * �Զ��ؽ������������ݽ��з��ࡣ��������ת������������
	 * @param pramerType	Ҫת��������
	 * @param pars			��Ҫת��������
	 * @return				����һ��Object
	 */
	public static Object[] StringGetObjs(String pramerType,String pars[])
	{

		//�������ж�һ���ǲ����ַ�������
		if (StaticString.StringArray.equals(pramerType))
		{
			return new Object[]{pars};
		}
		//ת����Int[]
		if (StaticString.IntArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				int temp[] = new int[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Integer.parseInt(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Int));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Int));
				return null;
			}
		}
		//ת����double[]
		if (StaticString.DoubleArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				double temp[] = new double[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Double.parseDouble(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Double));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Double));
				return null;
			}
		}
		//ת����float[]
		if (StaticString.FloatArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				float temp[] = new float[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Float.parseFloat(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Float));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Float));
				return null;
			}
		}
		//ת����char[]
		if (StaticString.CharArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				char temp[] = new char[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = pars[i].toCharArray()[0];
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Char));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Char));
				return null;
			}
		}
		//ת����boolean[]
		if (StaticString.BooleanArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				boolean [] temp = new boolean[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					if (StaticString.True.equals(pars[0]) || StaticString.False.equals(pars[0]))
					{
						temp[i] = Boolean.parseBoolean(pars[0]);
					}else 
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Boolean));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Boolean));
				return null;
			}
		}
		//ת����long����[]
		if (StaticString.LongArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				long temp[] = new long[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Long.parseLong(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Long));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Long));
				return null;
			}
		}
		//ת����short[]
		if (StaticString.ShortArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				short temp[] = new short[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Short.parseShort(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Short));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Short));
				return null;
			}
		}
		//ת����byte[]
		if (StaticString.ByteArray.equals(pramerType))
		{
			if (pars.length > 0)
			{
				byte temp[] = new byte[pars.length];
				for (int i = 0; i < pars.length; i++)
				{
					try
					{
						temp[i] = Byte.parseByte(pars[i]);
					} catch (Exception e)
					{
						//����ת���쳣
						System.err.println(DataArrayTurnException(pars[i], StaticString.Byte));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//ǰ�˴�����������Ϊ��
				System.err.println(DataNullException(StaticString.Byte));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * ����ת���쳣
	 * @param data		��Ҫת��������
	 * @param TurnTo	��Ҫת��������
	 * @return			����һ�������쳣
	 */
	private static String DataTurnException(String data,String TurnTo)
	{
		return "\n================Error:����ת���쳣��\""+data+"\" ->"+TurnTo+"��================\n";
	}
	/**
	 * ����ת���쳣
	 * @param data		��Ҫת��������
	 * @param TurnTo	��Ҫת����������
	 * @return			���صı����쳣
	 */
	private static String DataArrayTurnException(String data,String TurnTo)
	{
		return "\n================Error:����ת���쳣��\""+data+"\" ->"+TurnTo+"��================\n";
	}
	
	/**
	 * ǰ�˴���������Ҫת��������Ϊ��
	 * @param TurnTo	ת����Ŀ������
	 * @return			���صı����쳣
	 */
	private static String DataNullException(String TurnTo)
	{
		return "\n================Error:ǰ�˴�����������Ϊ�գ�\"\" ->"+TurnTo+"��================\n";
	}
	
}
