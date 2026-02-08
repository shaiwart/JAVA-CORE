


#### Shared resource
```java
class SharedBuffer {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while (available) {
            wait();  // wait until data is consumed
        }

        data = value;
        available = true;
        System.out.println("Produced: " + data);

        notify(); // wake consumer
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait(); // wait until data is produced
        }

        System.out.println("Consumed: " + data);
        available = false;

        notify(); // wake producer
    }
}

```
#### Producer thread
```java
class Producer extends Thread {
    private SharedBuffer buffer;

    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            buffer.produce(10);
        } catch (InterruptedException e) {}
    }
}

```
#### Consumer thread
```java
class Consumer extends Thread {
    private SharedBuffer buffer;

    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            buffer.consume();
        } catch (InterruptedException e) {}
    }
}
```

#### Execution Flow
Consumer starts first
→ no data
→ calls wait()
→ releases lock

Producer runs
→ produces data
→ calls notify()

Consumer wakes up
→ acquires lock
→ consumes data

#### Thread resumes after wait ( )
When a thread calls wait(), it resumes execution from the same point where it called wait(), not from the beginning of the method.
This is similar to sleep() in that sense—but the key difference is that wait releases the lock, while sleep does not.

```java
synchronized(lock) {
    while (!condition) {
        lock.wait();
    }

    // continues here when condition becomes true
}

// Here the thread resumes after wait(),
// but the loop checks the condition again,
// which sometimes gives the impression that
// execution restarted—but actually it just re-evaluates the loop.
```

**Which thread is resumed after notify**
notify() - wakes one arbitrary thread from the wait pool (not guaranteed which one).
notifyAll() - wakes all waiting threads, and then they compete to acquire the lock.

