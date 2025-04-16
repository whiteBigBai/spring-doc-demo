package org.example.springdoc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springdoc.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
