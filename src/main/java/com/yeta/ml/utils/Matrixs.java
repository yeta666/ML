package com.yeta.ml.utils;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

import java.util.Arrays;

/**
 * 矩阵基本操作
 * Created by YETA666 on 2018/4/2 0002.
 */
public class Matrixs {

    public static void main(String[] args) {
        //自定义矩阵
        Matrix matrix1 = DenseMatrix.Factory.zeros(3, 3);
        for (int i = 0, num = 1; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix1.setAsDouble(num++, i, j);
            }
        }
        //生成随机矩阵，范围(0, 1)
        Matrix matrix2 = DenseMatrix.Factory.rand(3, 3);
        //生成随机矩阵，范围(-1, 1)
        Matrix matrix3 = DenseMatrix.Factory.randn(3, 3);
        //生成随机矩阵，都为1
        //生成随机矩阵(0, 1)
        Matrix matrix4 = DenseMatrix.Factory.ones(3, 3);
        //初始值
        System.out.println("matrix1\n" + matrix1);
        System.out.println("matrix2\n" + matrix2);
        System.out.println("matrix3\n" + matrix3);
        System.out.println("matrix4\n" + matrix4);
        //转置
        System.out.println("matrix1转置\n" + matrix1.transpose());
        //加
        System.out.println("matrix1 + matrix2\n" + matrix1.plus(matrix2));
        //减
        System.out.println("matrix1 - matrix2\n" + matrix1.minus(matrix2));
        //乘
        System.out.println("matrix1 * matrix2\n" + matrix1.mtimes(matrix2));
        //自乘2
        System.out.println("matrix1 * 2\n" + matrix1.times(2));
        //逆
        System.out.println("matrix2 ^ (-1)\n" + matrix2.inv());
        //行列式
        System.out.println(matrix2.det());
        //行数、列数
        System.out.println(Arrays.toString(matrix1.getSize()));
    }
}
