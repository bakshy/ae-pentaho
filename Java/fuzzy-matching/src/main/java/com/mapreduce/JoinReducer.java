package com.mapreduce;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.entity.PersonsDistance;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class JoinReducer extends Reducer<Text, TextPair, Text, Text> {
    private PersonsDistance distance   = new PersonsDistance();
    private final List<Text> rightSide = new ArrayList<Text>();
    private final List<Text> leftSide  = new ArrayList<Text>();



    @Override
    protected void reduce(Text key, Iterable<TextPair> values, Context context) throws IOException, InterruptedException {

            rightSide.clear();
            leftSide.clear();

            for (TextPair t : values) {
               if (t.getSecond().equals(new Text("0")))
                    rightSide.add(new Text(t.getFirst()));

               else if (t.getSecond().equals(new Text("1")))
                    leftSide.add(new Text(t.getFirst()));

            }


            for (Text v1: rightSide){
                for (Text v2:leftSide) {

                    context.write(key, new Text( ",im,"+ v1.toString()+",cb,"+ v2.toString()+ ","+ distance.calcDistance(v1.toString(), v2.toString())));
                }
            }




        }
}




