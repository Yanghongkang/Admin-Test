package cn.sh.hadoop.AdminYhk_Hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class hbase {
	static Configuration conf = null;
	static HBaseAdmin hAdmin = null;

	static {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "localhost");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			hAdmin = new HBaseAdmin(conf);
		} catch (MasterNotRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		String tableName = "student";
		// 第一步：创建数据库表：“student”
		String[] columnFamilys = { "info", "course" };
		createTable(tableName, columnFamilys);
		//deleteTable(tableName);
	}

	//
	public static void deleteTable(String tableName) throws Exception {
		if (hAdmin.tableExists(tableName)) {
			try {
				hAdmin.disableTable(tableName);
				hAdmin.deleteTable(tableName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		hAdmin.close();
	}

	// 创建数据库表
	public static void createTable(String tableName, String[] columnFamilys) throws Exception {
		// 新建一个数据库管理员
		if (hAdmin.tableExists(tableName)) {
			System.out.println("表 " + tableName + " 已存在！");
			System.exit(0);
		} else {
			// 新建一个students表的描述
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			// 在描述里添加列族
			for (String columnFamily : columnFamilys) {
				tableDesc.addFamily(new HColumnDescriptor(columnFamily));
			}
			// 根据配置好的描述建表
			hAdmin.createTable(tableDesc);
			System.out.println("创建表 " + tableName + " 成功!");
		}
		hAdmin.close();
	}
}
