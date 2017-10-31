package cn.sh.hadoop.AdminYhk_Hadoop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

public class HDFSTest {
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		String uri = "hdfs://127.0.0.1:9000";
		FileSystem fs = null;
		try {

			fs = FileSystem.get(URI.create(uri), conf);
			// hadoopNodeStatus(fs);
			// llAndDir(fs, "/");
			// delFile(fs, "/sd");
			System.out.println(delFile(fs, "/tmp/testdir12"));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除文件
	 * @param fs
	 * @param pathOrFile
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public static boolean delFile(FileSystem fs, String pathOrFile) throws IllegalArgumentException, IOException {
		if (existsFile(fs, pathOrFile)) {
			fs.delete(new Path(pathOrFile), true);
			return true;
		} else {
			return false;
		}

	}
	/**
	 * 检查文件是否存在
	 * @param fs
	 * @param pathOrFile
	 * @return
	 * @throws IOException
	 */
	public static boolean existsFile(FileSystem fs, String pathOrFile) throws IOException {
		Path path = new Path(pathOrFile);
		if (fs.exists(path)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 列出文件目錄
	 * 
	 * @param fs
	 * @param urlpath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void llAndDir(FileSystem fs, String urlpath) throws FileNotFoundException, IOException {
		Path[] paths = new Path[1];
		paths[0] = new Path(urlpath);
		FileStatus[] status = fs.listStatus(paths);
		Path[] listedPaths = FileUtil.stat2Paths(status);
		for (Path p : listedPaths) {
			System.out.println(p);
		}

	}

	/**
	 * 查看狀態
	 * 
	 * @param fs
	 * @throws IOException
	 */
	public static void hadoopNodeStatus(FileSystem fs) throws IOException {
		DistributedFileSystem hdfs = (DistributedFileSystem) fs;
		DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
		for (int i = 0; i < dataNodeStats.length; i++) {
			System.out.println("DataNode_" + i + "_Name:" + dataNodeStats[i].getHostName());
		}

	}
}
