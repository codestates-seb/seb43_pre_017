package com.homunculus.preproject.utils;

import com.homunculus.preproject.exception.BusinessLogicException;
import com.homunculus.preproject.exception.ExceptionCode;
import com.homunculus.preproject.member.entity.Member;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CustomBeanUtils {

//    public static <T> void checkAllowedMember (T object, ExceptionCode code) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member connectedMember = (Member) authentication.getPrincipal();
//
//        try {
//            Field memberField = object.getClass().getDeclaredField("member");
//            memberField.setAccessible(true);
//
//            if (memberField.getType().equals(Member.class)) {
//                Member member = (Member) memberField.get(object);
//                if (member.getEmail().equals(connectedMember.getEmail())) {
//                    throw new BusinessLogicException(code);
//                }
//            } else {
//                throw new BusinessLogicException(ExceptionCode.MEMBER_FIELD_NOT_FOUND);
//            }
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new BusinessLogicException(ExceptionCode.MEMBER_FIELD_NOT_FOUND);
//        }
//    }

    public <T> T copyNonNullProperties(T source, T destination) {
        if (source == null || destination == null || source.getClass() != destination.getClass()) {
            return null;
        }

        final BeanWrapper src = new BeanWrapperImpl(source);
        final BeanWrapper dest = new BeanWrapperImpl(destination);


        for (final Field property : source.getClass().getDeclaredFields()) {
            Object sourceProperty = src.getPropertyValue(property.getName());
            if (sourceProperty != null && !(sourceProperty instanceof Collection<?>)) {
                dest.setPropertyValue(property.getName(), sourceProperty);
            }
        }

        return destination;
    }
}
