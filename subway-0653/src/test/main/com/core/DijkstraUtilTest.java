package test.main.com.core; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.junit.*;

import  main.com.core.*;
import  main.com.data.*;
import  main.com.demo.*;
import  main.com.model.*;

import java.io.File;
import  java.util.*;
/** 
* DijkstraUtil Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 25, 2019</pre> 
* @version 1.0 
*/ 
public class DijkstraUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: calculate(Station star, Station end) 
* 
*/ 
@Test
public void testCalculate() throws Exception { 
//TODO: Test goes here...
   try{
    Station start = new Station("");
    Station end = new Station("复兴路","6");

    Result  result = new Result();
    result.setStar(start);
    result.setEnd(end);
    result.setDistance(new Double(2));
    List<Station> passStations = new ArrayList<>();
    passStations.add(new Station("西站","1"));
    passStations.add(new Station("复兴路","6"));
    result.setPassStations(passStations);




    if(start.getName().equals("")||end.getName().equals(""))
        throw new MyNullStationNameException("必须传入合法的初始站和结尾站");


    Result  value = DijkstraUtil.calculate(start,end);

    Assert.assertEquals(result.toString(),value.toString());
   }catch (MyNullStationNameException e){
       System.out.println(e.toString());
   }
} 

/** 
* 
* Method: getLinkStations(Station station) 
* 
*/ 
@Test
public void testGetLinkStations() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getResultToText(String filePath) 
* 
*/ 
@Test
public void testGetResultToText() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: getNextStation() 
* 
*/ 
@Test
public void testGetNextStation() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = DijkstraUtil.getClass().getMethod("getNextStation"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: doubleAdd(double v1, double v2) 
* 
*/ 
@Test
public void testDoubleAdd() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = DijkstraUtil.getClass().getMethod("doubleAdd", double.class, double.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
