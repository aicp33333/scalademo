package com.functionalscala

/**
  * Created by 2017/11/24.
  *
  * @author rongpei
  */
object Demo {


  def fib (n:Int) : Int ={
      val first = 0
      val second = 1
      def go(n : Int,acc1 :Int,acc2 :Int) :Int =
        if(n == 0) acc2
        else go((n-1),acc2,(acc2+acc1))

    go(n,first,second)
  }


  def isSorted[A](as :Array[A],ordered:(A,A)=>  Boolean ):Boolean ={

     val first = as(0)

     def iteration( n:Int ): Boolean ={
       if((n+1) > as.length)
          true
       else if(!ordered(as(n),as(n + 1)))
          false
        else
         iteration(n+1)
     }

    iteration(0)
  }


  def ordered(a :Int , b : Int ) : Boolean ={
    (a - b) >0
  }

  def main(args: Array[String]): Unit = {
     println(fib(10))
  }

}
