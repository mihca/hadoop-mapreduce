package com.msg.xt.hadoop.bikesharing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BikeSharingTest {

	MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
	MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;

	@Before
	public void setUp() {
		WeatherMapper mapper = new WeatherMapper();
		SumReducer reducer = new SumReducer();
		mapDriver = new MapDriver<LongWritable, Text, Text, IntWritable>();
		mapDriver.setMapper(mapper);
		reduceDriver = new ReduceDriver<Text, IntWritable, Text, IntWritable>();
		reduceDriver.setReducer(reducer);
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapOnly() throws IOException {

		mapDriver.withInput(new LongWritable(1), new Text(
				"2011-01-01 00:00:00,1,0,0,1,9.84,14.395,81,0,3,13,16"));
		mapDriver.withInput(new LongWritable(2), new Text(
				"2011-01-01 01:00:00,1,0,0,1,9.02,13.635,80,0,8,32,40"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"2011-01-01 16:00:00,1,0,0,2,17.22,21.21,82,19.9995,41,52,93"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"2011-01-01 17:00:00,1,0,0,2,18.04,21.97,82,19.0012,15,52,67"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"2011-01-01 18:00:00,1,0,0,3,17.22,21.21,88,16.9979,9,26,35"));

		mapDriver.withOutput(new Text("1"), new IntWritable(16));
		mapDriver.withOutput(new Text("1"), new IntWritable(40));
		mapDriver.withOutput(new Text("2"), new IntWritable(93));
		mapDriver.withOutput(new Text("2"), new IntWritable(67));
		mapDriver.withOutput(new Text("3"), new IntWritable(35));
		mapDriver.runTest();
	}

	@Test
	public void testMapReduce() throws IOException {
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(16));
		values.add(new IntWritable(40));
		reduceDriver.withInput(new Text("1"), values);
		reduceDriver.withOutput(new Text("1"), new IntWritable(56));
		reduceDriver.runTest();
	}
}
