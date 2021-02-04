package com.ashan.hadoop.numberaverage;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AverageMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static Text tenth = new Text();
    private final static IntWritable returnVal = new IntWritable();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        int intVal = Integer.parseInt(value.toString());
        tenth.set("" + (intVal % 10));
        returnVal.set(intVal);

        context.write(tenth, returnVal);
    }
}
