package com.mapreduce;


import static org.mockito.Mockito.*;
import java.io.IOException;
import org.apache.hadoop.io.*;
import org.junit.*;


public class Mapper2Test {


    @Test
    public void processesValidRecord() throws IOException, InterruptedException {
        Mapper2 mapper = new Mapper2();

        String  strvalue = "1,Ivanovich,Ivan,Petrov,emailII@mail.ru,234324234,234342134";
        Text value = new Text(strvalue);

        Mapper2.Context context =  mock(Mapper1.Context.class);
        mapper.map(null, value, context );

        verify(context).write(new Text("ptrv"), new TextPair(strvalue, "1"));

    }

}
