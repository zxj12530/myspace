package com.XEasy.Controller.Util;

public class PageTurn
{
	/**
	 * 自动呢将传过来的数据进行分类。数据类型转换，只是一个参数
	 * @param pramerType	要转换的类型
	 * @param pars			需要转换的数据
	 * @return				返回一个Object
	 */
	public static Object[] StringGetObj(String pramerType,String pars[])
	{
		//最首先判断一下是不是字符串
		if (StaticString.String.equals(pramerType))
		{
			return new Object[]{pars[0]};
		}
		//转换成Int
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
					System.err.println(DataTurnException(pars[0], StaticString.Int));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Int));
				return null;
			}
		}
		//转换成double
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
					System.err.println(DataTurnException(pars[0], StaticString.Double));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Double));
				return null;
			}
		}
		//转换成float
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
					System.err.println(DataTurnException(pars[0], StaticString.Float));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Float));
				return null;
			}
		}
		//转换成char
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
					System.err.println(DataTurnException(pars[0], StaticString.Char));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Char));
				return null;
			}
		}
		//转换成boolean
		if (StaticString.Boolean.equals(pramerType))
		{
			if (!pars[0].equals(""))
			{
				if (StaticString.True.equals(pars[0]) || StaticString.False.equals(pars[0]))
				{
					return new Object[]{Boolean.parseBoolean(pars[0])};
				}else 
				{
					System.err.println(DataTurnException(pars[0], StaticString.Boolean));//报出数据转换异常
				}
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Boolean));
				return null;
			}
		}
		//转换成long类型
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
					System.err.println(DataTurnException(pars[0], StaticString.Long));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Long));
				return null;
			}
		}
		//转换成short
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
					System.err.println(DataTurnException(pars[0], StaticString.Short));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Short));
				return null;
			}
		}
		//转换成byte
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
					System.err.println(DataTurnException(pars[0], StaticString.Byte));//报出数据转换异常
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Byte));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 自动呢将传过来的数据进行分类。数据类型转换，参数数组
	 * @param pramerType	要转换的类型
	 * @param pars			需要转换的数据
	 * @return				返回一个Object
	 */
	public static Object[] StringGetObjs(String pramerType,String pars[])
	{

		//最首先判断一下是不是字符串数组
		if (StaticString.StringArray.equals(pramerType))
		{
			return new Object[]{pars};
		}
		//转换成Int[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Int));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Int));
				return null;
			}
		}
		//转换成double[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Double));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Double));
				return null;
			}
		}
		//转换成float[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Float));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Float));
				return null;
			}
		}
		//转换成char[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Char));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Char));
				return null;
			}
		}
		//转换成boolean[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Boolean));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Boolean));
				return null;
			}
		}
		//转换成long类型[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Long));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Long));
				return null;
			}
		}
		//转换成short[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Short));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Short));
				return null;
			}
		}
		//转换成byte[]
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
						//数组转换异常
						System.err.println(DataArrayTurnException(pars[i], StaticString.Byte));
						return null;
					}
				}
				return new Object[]{temp};
			}else 
			{
				//前端传过来的数据为空
				System.err.println(DataNullException(StaticString.Byte));
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 数据转换异常
	 * @param data		需要转换的数据
	 * @param TurnTo	需要转换的类型
	 * @return			返回一个报错异常
	 */
	private static String DataTurnException(String data,String TurnTo)
	{
		return "\n================Error:数据转换异常（\""+data+"\" ->"+TurnTo+"）================\n";
	}
	/**
	 * 数组转换异常
	 * @param data		需要转换的数据
	 * @param TurnTo	需要转换至的类型
	 * @return			返回的报错异常
	 */
	private static String DataArrayTurnException(String data,String TurnTo)
	{
		return "\n================Error:数组转换异常（\""+data+"\" ->"+TurnTo+"）================\n";
	}
	
	/**
	 * 前端传过来的需要转换的数据为空
	 * @param TurnTo	转换至目标类型
	 * @return			返回的报错异常
	 */
	private static String DataNullException(String TurnTo)
	{
		return "\n================Error:前端传过来的数组为空（\"\" ->"+TurnTo+"）================\n";
	}
	
}
