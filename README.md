Try to find bug 
==

[Here](src/main/kotlin/FutureUtils.kt) is method that converts java's **CompletableFuture** into rxjava2 **Observable**.
This implementation contains bug(s).  

There are [tests](src/test/kotlin/FutureUtilsKtTest.kt) that showcase the bug.
Can you make it pass? 

