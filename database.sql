create table IF NOT EXISTS Member (
    id bigint NOT NULL AUTO_INCREMENT,
    profileUrl VARCHAR(100),
    username VARCHAR(20) not null,
    socialId VARCHAR(30) not null,
    socialType VARCHAR(20) not null check ( socialType in ('google')) ,
    PRIMARY KEY (id)
);

create table IF NOT EXISTS Video (
    id bigint NOT NULL AUTO_INCREMENT,
    userId bigint not null,
    url varchar(100),
    foreign key (userId) references Member(id),
    primary key (id)
);

create table IF NOT EXISTS Comment (
    id bigint NOT NULL AUTO_INCREMENT,
    videoId bigint not null,
    userId bigint not null,
    content text not null,
    foreign key (videoId) references Video(id),
    foreign key (userId) references Member(id),
    primary key (id)
);

create table IF NOT EXISTS Subscription (
    following bigint not null,
    follower bigint not null,
    foreign key (following) references Member(id),
    foreign key (follower) references Member(id),
    primary key (following, follower)
);

create table IF NOT EXISTS VideoLike (
    userId bigint not null,
    videoId bigint not null,
    foreign key (userId) references Member(id),
    foreign key (videoId) references Video(id),
    primary key (userId, videoId)
);

create table IF NOT EXISTS CommentLike (
    userId bigint not null,
    commentId bigint not null,
    foreign key (userId) references Member(id),
    foreign key (commentId) references Comment(id),
    primary key (userId, commentId)
);