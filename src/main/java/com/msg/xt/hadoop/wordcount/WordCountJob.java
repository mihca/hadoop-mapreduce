package com.msg.xt.hadoop.wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.msg.xt.hadoop.bikesharing.BikeJob;


public class WordCountJob extends Configured implements Tool {

	  public int run(String[] args) throws Exception {

		Job job = Job.getInstance(getConf(), "wordcount");
	    job.setJarByClass(getClass());
	    job.setJobName(getClass().getSimpleName());

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

	    FileInputFormat.addInputPath(job, new Path(args[0]));
	    FileOutputFormat.setOutputPath(job, new Path(args[1]));

	    job.setMapperClass(WordMapper.class);
	    // job.setCombinerClass(LongSumReducer.class);
	    job.setReducerClass(SumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setJarByClass(WordCountJob.class);

	    return job.waitForCompletion(true) ? 0 : 1;
	  }

	  public static void main(String[] args) throws Exception {
	    int rc = ToolRunner.run(new BikeJob(), args);
	    System.exit(rc);
	  }
	}
