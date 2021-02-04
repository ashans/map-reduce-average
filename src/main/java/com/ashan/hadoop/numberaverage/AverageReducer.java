package com.ashan.hadoop.numberaverage;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AverageReducer extends Reducer<Text, IntWritable, IntWritable, IntWritable> {

    private IntWritable result = new IntWritable();
    private IntWritable countVal = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        int count = 0;
        for (IntWritable val : values) {
            sum += val.get();
            count++;
        }

        result.set(sum);
        countVal.set(count);

        context.write(result, countVal);
    }
}
