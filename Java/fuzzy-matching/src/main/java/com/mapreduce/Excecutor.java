package com.mapreduce;


/**
 *
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Partitioner;

import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.*;


public class Excecutor extends Configured implements Tool {
    public static class KeyPartitioner extends Partitioner<TextPair, TextPair> {
        @Override
        public int getPartition(TextPair key, TextPair value, int numPartitions) {
            return (key.getFirst().hashCode() & Integer.MAX_VALUE) % numPartitions;
        }
    }
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 3) {
           // JobBuilder.printUsage(this, "<ncdc input> <station input> <output>");
            return -1;
        }
        Job job = new Job(getConf(), "Join cb and im");

        job.setJarByClass(getClass());
        Path im = new Path(args[0]);
        Path cb = new Path(args[1]);
        Path outputPath = new Path(args[2]);

        MultipleInputs.addInputPath(job, im, TextInputFormat.class, Mapper1.class);
        MultipleInputs.addInputPath(job, cb, TextInputFormat.class, Mapper2.class);
        FileOutputFormat.setOutputPath(job, outputPath);


        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TextPair.class);

        job.setPartitionerClass(KeyPartitioner.class);
        job.setGroupingComparatorClass(TextPair.FirstComparator.class);

        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(Text.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Excecutor(), args);
        System.exit(exitCode);
    }
}


