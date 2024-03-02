# Thread-Safe In-Memory Caching Library

This repository contains a Java implementation of a thread-safe in-memory caching library with support for multiple eviction policies.

## Overview

The caching library provides a thread-safe interface for storing and retrieving key-value pairs in memory. It supports the following eviction policies:

- FIFO (First-In-First-Out)
- LIFO (Last-In-First-Out)
- LRU (Least Recently Used
- LFU (Least Frequently Used)

Users can also implement custom eviction policies by implementing the `EvictionPolicy` interface.

## Requirements

To use this library, you need:

- Java Development Kit (JDK) version 8 or higher

## Installation

To use the library in your Java project, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/d-datta/InMemoryCachingLibrary.git
    cd InMemoryCachingLibrary
    ```

2. Build the project using your favorite Java build tool or IDE.

3. Add the library as a dependency in your Java project.

## Usage

To use the caching library in your Java code, follow these steps:

1. Import the necessary classes:

    ```java
    import main.cache.Cache;
    import main.cache.evictionpolicies.*;
    ```

2. Create a cache instance with the desired eviction policy:

    ```java
    // Example: Create a cache with LRU eviction policy and capacity of 100
    EvictionPolicy<Integer, String> lruEvictionPolicy = new LruEvictionPolicy<>();
    Cache<Integer, String> cache = new Cache<>(lruEvictionPolicy, 100);
    ```

3. Use the `put` method to add key-value pairs to the cache:

    ```java
    cache.put(1, "value1");
    cache.put(2, "value2");
    ```

4. Use the `get` method to retrieve values from the cache:

    ```java
    String value = cache.get(1);
    ```

5. Experiment with different eviction policies and cache capacities to suit your application's needs.

## Thread Safety

The caching library is designed to be thread-safe, allowing multiple threads to access and modify the cache concurrently.

## Custom Eviction Policies

Users can implement custom eviction policies by implementing the `EvictionPolicy` interface. This allows for flexibility in designing eviction strategies tailored to specific use cases.


