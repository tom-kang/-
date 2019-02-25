

import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		try {
			Boolean flag = true;

			while (flag) {
				System.out.println("输入 exit 退出, 直接回车进入下一步");
				System.out.println("请输入户头号：");
				Scanner s = new Scanner(System.in);
				String accountNo = s.nextLine();
				
				if ((accountNo != null) && ("".equals(accountNo))) {
					accountNo = "";
				}
				
				if ("exit".equals(accountNo)) {
					System.out.println("结束.........");
					return;
				}
				
				System.out.println("请输入表名或者直接回车(输入表名则显示完整的分表名)：");
				Scanner s1 = new Scanner(System.in);
				String tableName = s1.nextLine();
				
				if ((tableName != null) && ("".equals(tableName)))
					tableName = "";
				
				if ("exit".equals(accountNo)) {
					System.out.println("结束.........");
					return;
				}
				
				System.out.println("请输入分表数(不输入直接回车则默认20)：");
				Scanner s2 = new Scanner(System.in);
				String tableNum = s2.nextLine();
				
				if ((tableNum != null) && ("".equals(tableNum)))
					tableNum = "20";
				
				
				int intvalue = Integer.valueOf(tableNum);
				
				System.out.println("结果如下:");
				
				long num = getTableSuffix(accountNo, intvalue);
				
				System.out.println("分表号:"+ num);
				System.out.println("完整表名:"+ tableName + num );
				
				//System.out.println(getAccount(accountNo, tableName));
				
				System.out.println("输入exit退出,输入任意继续");
				Scanner s3 = new Scanner(System.in);
				String zhiling = s3.nextLine();
				if ("exit".equals(zhiling)) {
					flag = false;
				}
			}
		} catch (Exception e) {
			
			System.out.println("------异常----,请检查输入字符串, 分表数需要为数字！,请重新执行bat文件");
		}
	}

	public static String getAccount(String accountNo, String tableName) {
		return route(tableName, accountNo, Integer.valueOf(20));
	}

	public static String route(String mainTable, Object paramValue,
			Integer tableNum) {
		if ((tableNum != null) && (paramValue != null)) {
			int mod = paramValue.hashCode() % tableNum.intValue();
			if (mod < 0)
				mod = Math.abs(mod);

			return mainTable + mod;
		}
		return mainTable;
	}
	
	 /**
     * 获取表后缀
     *
     * @param paramValue 入参  -- -例如户头号 根据户头号分表
     * @param tableNum   分表数
     * @return
     */
    public static long getTableSuffix(String paramValue, int tableNum) {
        //对参数值hash
        long hashCode = DalUtils.HashAlgorithm.KETAMA_HASH.hash(
                DalUtils.computeMd5(paramValue), 0);
        return hashCode % tableNum;
    }
    
    
}