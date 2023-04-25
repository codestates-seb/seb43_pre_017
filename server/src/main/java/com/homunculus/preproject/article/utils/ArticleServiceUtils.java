package com.homunculus.preproject.article.utils;

import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;

public class ArticleServiceUtils {
    public static String checkValidTypeOfOrderByField(String typeForSorting) {
        if( typeForSorting == null )
            typeForSorting = "기본";

        TypeOfGetAll[] types = TypeOfGetAll.values();
        int index = -1;
        for (int i = 0; i < types.length; i++) {
            if (types[i].getType().equals(typeForSorting)) {
                index = i;
                break;
            }
        }

        if( index == -1 )
            throw new BusinessLogicException(ExceptionCode.REQUESTED_RANGE_NOT_SATISFIABLE);

        String orderBy = types[index].getOrderBy();
        return orderBy;
    }
}
