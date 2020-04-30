# 线程池
## 四种常见的线程池详解
* Executors.newCacheThreadPool()：
可缓存线程池，先查看池中有没有以前建立的线程，如果有，就直接使用。如果没有，就建一个新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务
* Executors.newFixedThreadPool(int n)：
创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程。
* Executors.newScheduledThreadPool(int n)：
创建一个定长线程池，支持定时及周期性任务执行
* Executors.newSingleThreadExecutor()：
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

# 高并发demo整理
