package com.metamong.server.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCharacters is a Querydsl query type for Characters
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCharacters extends EntityPathBase<Characters> {

    private static final long serialVersionUID = -1031190997L;

    public static final QCharacters characters = new QCharacters("characters");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath fileUrl = createString("fileUrl");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath name = createString("name");

    public QCharacters(String variable) {
        super(Characters.class, forVariable(variable));
    }

    public QCharacters(Path<? extends Characters> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCharacters(PathMetadata metadata) {
        super(Characters.class, metadata);
    }

}

