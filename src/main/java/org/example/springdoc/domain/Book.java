package org.example.springdoc.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("book")
@Data
public class Book {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String title;
    private String author;
}
