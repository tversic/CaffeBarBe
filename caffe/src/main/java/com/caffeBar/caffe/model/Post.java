package com.caffeBar.caffe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    Long postId;
    @Column
    String postTitle;
    @Column
    String postContent;

    public Post(String header, String content) {
        this.setPostTitle(header);
        this.setPostContent(content);
    }
}
