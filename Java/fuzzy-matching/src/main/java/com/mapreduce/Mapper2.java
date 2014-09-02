package com.mapreduce;


import java.io.IOException;

import com.entity.PersonRecordParser;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class Mapper2  extends Mapper<LongWritable, Text, Text, TextPair>  {
    // variables to process delivery report

    private PersonRecordParser parser = new PersonRecordParser();


    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {


        // parsing string for creating mach-code
        parser.parseInData(value);
        context.write(new Text( parser.getKey()), new TextPair( parser.getValue(),"1"));


    }


}