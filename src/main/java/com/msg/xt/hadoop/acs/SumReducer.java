package com.msg.xt.hadoop.acs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	public void reduce(Text key, Iterable<LongWritable> values, Context context)
			throws IOException, InterruptedException {
		
		int sum = 0;
		StringBuffer valueList = new StringBuffer();
		for (LongWritable val : values) {
			sum += val.get();
			if (valueList.length()>0) valueList.append(",");
			valueList.append(val);
		}
		
		// System.out.println ("reduce: key=[" + key + "] value=[" + valueList + "]");
		
		context.write(key, new LongWritable(sum));
	}
}