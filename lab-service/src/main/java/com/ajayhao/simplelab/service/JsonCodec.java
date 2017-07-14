package com.ajayhao.simplelab.service;

import static javafx.scene.input.KeyCode.F;

/**
 * Created by AjayHao on 2017/7/13.
 *
 * 目标类型为json格式的字符串类型
 */
public interface JsonCodec<E> extends Codec<E, String> {

    @Override
    String encode(E object);

    @Override
    E decode(String object);

}
