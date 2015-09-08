package com.msg.xt.hadoop.acs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.msg.xt.hadoop.acs.model.StateResolver;

/*
 * Die 4 Type des Mapper repräsentieren: Input key, value und Output key, value
 * Der Input sind die ersten beiden Parameter der map-Funktion
 * Der Output ist das, was die Map-Funktion in den context schreibt
 */
public class StateMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
  
  private StateResolver stateResolver = new StateResolver();
	
  private Text stateText = new Text();

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {

	String[] split = value.toString().split(",");
	
	if (split.length>=6) {
		
		String stateCode = split[5];
	
		if ("true".equals(context.getConfiguration().get(StateJob.JOB_CONFIGURATION_MAPSTATENAME))) {
			String stateName = stateResolver.getStateText(stateCode);
			if (stateName != null) {
				stateText.set(stateName);
			} else {
				stateText.set("undefined");
			}
		} else {
			stateText.set(stateCode);
		}

		context.write(stateText, new LongWritable(1));
	}
  }
}
