package com.msg.xt.hadoop.bikesharing;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
  
  private Text weather = new Text();
  private IntWritable count = new IntWritable();

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	String[] split = value.toString().split(",");
    weather.set(split[4]);
    if (split.length == 12) {
      try {
        count.set(Integer.parseInt(split[11]));
        context.write(weather, count);
      } catch (NumberFormatException e) {
        // cannot parse - ignore
      }
    }
  }
}