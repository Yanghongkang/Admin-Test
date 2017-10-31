package cn.sh.hadoop.AdminYhk_Hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

//Administrator
public class WordCountExample {
	private static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable>{

		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {
			String str=value.toString();
			String []strArray=str.split(" ");
			for(String s:strArray){
				context.write(new Text(s), new IntWritable(1));
			}
		}
		
	}
	
	private static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Context context)
				throws IOException, InterruptedException {
			int sum=0;
			for(IntWritable count:values){
				sum+=count.get();
			}
			context.write(key, new IntWritable(sum));
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Configuration conf=new Configuration();
		String []argArray=new GenericOptionsParser(conf,args).getRemainingArgs();
		if(argArray.length!=2){
			System.out.println("需要两个参数");
			System.exit(1);
		}
		Job job=new Job(conf,"wordcount");
		job.setJarByClass(WordCountExample.class);
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setReducerClass(WordCountReducer.class);
		FileInputFormat.addInputPath(job, new Path(argArray[0]));
		FileOutputFormat.setOutputPath(job, new Path(argArray[1]));
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
