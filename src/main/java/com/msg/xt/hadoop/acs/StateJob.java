package com.msg.xt.hadoop.acs;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class StateJob extends Configured implements Tool {

	public static final String JOB_CONFIGURATION_MAPSTATENAME = "JOB_CONFIGURATION_MAPSTATENAME";
	
	public int run(String[] args) throws Exception {

		Job job = Job.getInstance(getConf());
		job.setJarByClass(getClass());
		job.setJobName(getClass().getSimpleName());

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.setMapperClass(StateMapper.class);
		job.setCombinerClass(SumReducer.class);
		job.setReducerClass(SumReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		if (args.length>2)
			job.getConfiguration().set(JOB_CONFIGURATION_MAPSTATENAME, args[2]);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int rc = ToolRunner.run(new StateJob(), args);
		System.exit(rc);
	}
}
