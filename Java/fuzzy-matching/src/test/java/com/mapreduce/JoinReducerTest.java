package com.mapreduce;



import static org.mockito.Mockito.*;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import com.entity.PersonsDistance;
import org.apache.hadoop.io.*;
import org.junit.*;

public class JoinReducerTest {

    @Test
    public void returnsValues() throws IOException, InterruptedException {
        PersonsDistance distance =new PersonsDistance();
        JoinReducer reducer = new JoinReducer();

        String  strvalue1 = "1,Ivan,Ivanovich,Petrov,emailII@mail.ru,(234)324234,234342134";
        String  strvalue2 = "2,Ivan,Ivanovich,Petrov,emailII@mail.ru,(234)324234,234342134";

        Text key = new Text("iipet");

        List<TextPair> values =  new ArrayList<TextPair>();

        values.add(new TextPair(strvalue1, "0"));
        values.add(new TextPair(strvalue2, "1"));


        JoinReducer.Context context =   mock(JoinReducer.Context.class);
        reducer.reduce(key, values, context);

        verify(context).write(key, new Text(",im,"+strvalue1 +",cb,"+strvalue2 +"," +distance.calcDistance(strvalue1, strvalue2)));
    }

}
