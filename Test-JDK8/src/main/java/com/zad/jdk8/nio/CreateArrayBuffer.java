package com.zad.jdk8.nio;// $Id$

import java.nio.ByteBuffer;

public class CreateArrayBuffer {
    static public void main(String args[]) throws Exception {



        byte array[] = new byte[1024];

        ByteBuffer buffer = ByteBuffer.wrap(array);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        buffer.flip();

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        // 证明确实是使用了数组作为缓冲区
        System.out.println((char) array[0]);
        System.out.println((char) array[1]);
        System.out.println((char) array[2]);
    }
}
