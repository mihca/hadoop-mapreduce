package com.msg.xt.hadoop.acs;

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
public class StateTest {

	MapReduceDriver<LongWritable, Text, Text, LongWritable, Text, LongWritable> mapReduceDriver;
	MapDriver<LongWritable, Text, Text, LongWritable> mapDriver;
	ReduceDriver<Text, LongWritable, Text, LongWritable> reduceDriver;

	@Before
	public void setUp() {
		StateMapper mapper = new StateMapper();
		SumReducer reducer = new SumReducer();
		mapDriver = new MapDriver<LongWritable, Text, Text, LongWritable>();
		mapDriver.setMapper(mapper);
		reduceDriver = new ReduceDriver<Text, LongWritable, Text, LongWritable>();
		reduceDriver.setReducer(reducer);
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, LongWritable, Text, LongWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	@Test
	public void testMapOnly() throws IOException {

		mapDriver.withInput(new LongWritable(1), new Text(
				"H,154,6,02500,3,01,1000000,1007549,00051,04,1,1,1,,1,03,02,2,2,2,,2,2,350,1,2,0002,003,2,3,0350,1,,2,,,,3,2,1,09,,,1,9,2,1,,1,1,2,1,,0025000,3,0480,2,1,1,,1,000151000,4,0,,,1,1,000151000,0,4,4,4,1,1,1,6,00,04,0,0,00,003,0,1,0,0,0,0,2,00426,,0,0,1,03,2,01,01,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,00045,00052,00053,00050,00100,00079,00078,00050,00019,00057,00089,00046,00067,00109,00051,00018,00017,00018,00047,00087,00049,00050,00049,00064,00013,00016,00016,00055,00086,00048,00014,00049,00050,00011,00056,00083,00071,00108,00054,00081,00043,00053,00050,00063,00017,00015,00015,00047,00088,00047,00014,00052,00051,00020,00053,00084,00078,00074,00053,00015,00054,00055,00058,00065,00110,00082,00089,00050,00012,00055,00086,00053,00059,00084,00049,00015,00015,00020,00050,00016"));
		mapDriver.withInput(new LongWritable(2), new Text(
				"H,300604,9,06502,4,06,1000000,1007549,00017,01,1,1,1,,1,03,02,1,2,2,,2,2,160,2,2,0400,040,1,1,2300,1,,1,1,01700,1,1,2,1,05,,,1,9,2,1,00580,1,1,1,1,,0300000,1,1000,8,,0,,0,,,0,,,1,4,000090000,0,4,4,4,1,1,1,5,00,,0,0,00,035,0,1,0,0,0,0,1,02596,1,0,0,1,62,,,,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00017,00033,00016,00006,00029,00028,00005,00018,00019,00006,00017,00006,00016,00018,00018,00034,00016,00028,00017,00026,00017,00005,00016,00028,00006,00005,00031,00016,00019,00031,00018,00036,00017,00018,00020,00005,00014,00004,00017,00004,00017,00034,00017,00006,00029,00030,00006,00019,00019,00006,00016,00006,00017,00016,00018,00035,00018,00029,00017,00029,00016,00006,00017,00029,00006,00004,00031,00017,00017,00032,00019,00030,00017,00016,00018,00006,00017,00006,00017,00004"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"H,300609,9,10701,4,06,1000000,1007549,00063,05,1,1,1,,1,04,02,1,2,2,,2,1,180,2,2,0002,020,1,3,,1,,2,,,,,2,1,06,2,00850,1,9,2,1,,1,1,3,1,,,2,0580,5,1,0,0,0,000060000,2,,1098,022,2,1,000060000,0,2,2,2,1,1,1,1,02,05,0,0,02,,0,1,0,1,0,0,1,,,1,0,0,,2,01,01,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00020,00065,00100,00084,00022,00062,00077,00060,00019,00063,00027,00111,00067,00105,00063,00078,00073,00114,00110,00062,00067,00100,00047,00020,00063,00018,00016,00105,00055,00108,00081,00059,00112,00068,00098,00019,00014,00065,00058,00019,00020,00056,00097,00071,00019,00062,00070,00062,00023,00065,00021,00114,00069,00090,00067,00069,00074,00127,00113,00078,00070,00123,00061,00017,00062,00018,00015,00093,00081,00125,00073,00063,00107,00062,00105,00019,00018,00053,00048,00019"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"H,300651,9,01303,4,06,1000000,1007549,00077,02,1,1,1,,1,03,02,1,2,2,,2,2,080,2,2,0002,030,1,3,0700,1,,1,1,01900,1,1,2,1,06,,,1,9,2,1,00670,1,1,1,1,,0300000,2,0050,5,1,0,,0,000085000,4,1,,,1,1,000085000,0,4,4,4,1,1,1,6,00,02,0,0,00,038,0,1,0,0,0,0,3,02684,2,0,0,1,42,2,01,01,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,00067,00137,00134,00088,00074,00067,00123,00020,00119,00073,00027,00022,00020,00067,00119,00024,00072,00066,00075,00083,00070,00113,00123,00076,00081,00075,00121,00024,00125,00094,00025,00026,00028,00076,00126,00021,00107,00068,00069,00066,00067,00132,00125,00079,00084,00083,00134,00019,00133,00068,00024,00023,00020,00082,00124,00020,00088,00076,00080,00070,00080,00132,00140,00079,00077,00071,00141,00025,00129,00076,00020,00018,00022,00087,00129,00022,00072,00079,00076,00083"));
		mapDriver.withInput(new LongWritable(1), new Text(
				"H,702154,3,01001,2,18,1000000,1007549,00061,02,1,3,1,,1,01,02,,2,2,,,,050,,2,0002,100,2,1,0540,2,,,,,,3,,1,04,,,1,9,,1,,1,1,2,1,,0080000,2,0540,3,4,0,,0,000026000,4,0,,,1,1,000026000,0,4,4,4,1,1,1,7,00,02,0,0,00,012,0,1,0,0,2,2,2,00257,,0,0,1,06,0,09,09,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,00103,00060,00015,00100,00016,00062,00054,00059,00059,00067,00066,00104,00020,00077,00060,00022,00016,00048,00100,00056,00058,00018,00057,00059,00055,00017,00114,00110,00103,00096,00022,00063,00065,00084,00020,00057,00062,00020,00072,00021,00016,00071,00107,00020,00124,00052,00066,00057,00064,00054,00057,00017,00094,00056,00062,00090,00103,00072,00019,00061,00058,00113,00062,00055,00062,00106,00018,00016,00020,00017,00101,00071,00069,00020,00095,00063,00052,00101,00054,00115"));

		mapDriver.withOutput(new Text("01"), new LongWritable(1));
		mapDriver.withOutput(new Text("06"), new LongWritable(1));
		mapDriver.withOutput(new Text("06"), new LongWritable(1));
		mapDriver.withOutput(new Text("06"), new LongWritable(1));
		mapDriver.withOutput(new Text("18"), new LongWritable(1));
		
		mapDriver.runTest();
	}

	@Test
	public void testMapReduce() throws IOException {
		
		List<LongWritable> values1 = new ArrayList<LongWritable>();
		values1.add(new LongWritable(1));
		List<LongWritable> values3 = new ArrayList<LongWritable>();
		values3.add(new LongWritable(1));
		values3.add(new LongWritable(1));
		values3.add(new LongWritable(1));
		
		reduceDriver.withInput(new Text("Alabama"), values1);
		reduceDriver.withInput(new Text("California"), values3);
		reduceDriver.withInput(new Text("Indiana"), values1);
		reduceDriver.withOutput(new Text("Alabama"), new LongWritable(1));
		reduceDriver.withOutput(new Text("California"), new LongWritable(3));
		reduceDriver.withOutput(new Text("Indiana"), new LongWritable(1));
		
		reduceDriver.runTest();
	}
}
