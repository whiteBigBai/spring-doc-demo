package org.example.springdoc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springdoc.domain.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
