# Redis内存淘汰策略

1、noevication，新写入操作报错。

测试代码

```
@Test
public void noevicationTest() {
    //flush db
    redisTemplate.delete(redisTemplate.keys("*"));
    //1k
    byte[] bytes = new byte[1024];

    int i = 0;
    while (true) {
        try {
            redisTemplate.opsForValue().set(String.valueOf(i), bytes);
            System.out.println(i++);
        } catch (Exception e) {
            e.printStackTrace();
            break;
        }
    }
}
```

在执行到i为145的时候抛出了异常，有点费解，value为1k，key顶多就几b，把redis最大内存改为10m，可以到7000多个，不知道还有什么占用了内存。

2、volatile-lru，使用LRU算法删除设置了expire的key 注：redis使用的是一种伪LRU算法，应该是出于性能考虑

LRU（Least recently used，最近最少使用）算法根据数据的历史访问记录来进行淘汰数据，其核心思想是“如果数据最近被访问过，那么将来被访问的几率也更高”。

测试代码

```
@Test
public void volatileTest() {
    //flush db
    redisTemplate.delete(redisTemplate.keys("*"));
    //1k
    byte[] bytes = new byte[1024];

    int i = 0;
    for (; i < 100; i++) {
        redisTemplate.opsForValue().set(String.valueOf(i), bytes, 10, TimeUnit.MINUTES);
        System.out.println(i);
    }

    for (; i < 200; i++) {
        redisTemplate.opsForValue().set(String.valueOf(i), bytes);
        System.out.println(i);
    }
}
```

该测试结果是i为前100设置了expire的key被删除了部分，使用volatile-lru重新执行noevicationTest方法，内存不足时也会抛出异常

3、allkeys-lru，使用LRU算法（最近最少使用）删除key

测试代码

```
@Test
public void allkeysTest() throws InterruptedException {
    //flush db
    redisTemplate.delete(redisTemplate.keys("*"));
    //1k
    byte[] bytes = new byte[1024];

    int i = 0;
    for (; i < 100; i++) {
        redisTemplate.opsForValue().set(String.valueOf(i), bytes);
        System.out.println(i);
    }

    Thread.sleep(1000);
    for (; i < 200; i++) {
        redisTemplate.opsForValue().set(String.valueOf(i), bytes);
        System.out.println(i);
    }
}
```

i为前100设置了expire的key被删除了部分，去掉sleep的话i为后100的key也被删除部分。

4、volatile-lfu，使用LFU算法删除设置了expire的key 注：使用的也是一种伪LFU算法

LFU（Least Frequently Used）算法根据数据的历史访问频率来淘汰数据，其核心思想是“如果数据过去被访问多次，那么将来被访问的频率也更高”。

执行volatileTest方法

结果同2

5、allkeys-lfu，使用LFU算法删除key

执行allKeysTest方法

结果同3

6、volatile-random，随机删除设置了expire的key

执行volatileTest方法

i为前100设置了expire的key被随机删除了部分

7、allkeys-random，随机删除key

执行allKeysTest

key被随机删除部分

8、volatile-ttl，按expire删除key，越早过期的越快删除

测试代码

```
@Test
public void volatileTtlTest() {
    //flush db
    redisTemplate.delete(redisTemplate.keys("*"));
    //1k
    byte[] bytes = new byte[1024];

    for (int i = 0; i < 1000; i++) {
        redisTemplate.opsForValue().set(String.valueOf(i), bytes, i + 1, TimeUnit.MINUTES);
        System.out.println(i);
    }
}
```

i为800前的key全部被删除，800后的被删除部分（极少并且基本在850之前），可见越早过期的越快删除也不是一定的，应该是跟lru、lfu一样并不能达到绝对精确的删除，个人觉得也不用绝对精确，根据项目的需要选择策略即可


写的单元测试只能算是个小demo，并没有特别去模拟LRU、LFU的场景，而且相信redis的测试肯定要比我做的要好得多了，这里就简单的了解学习一下。

[博客地址](https://blog.yk95.top)


