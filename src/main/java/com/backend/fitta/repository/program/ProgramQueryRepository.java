package com.backend.fitta.repository.program;

import com.backend.fitta.entity.gym.Program;
import com.backend.fitta.entity.gym.QProgram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.backend.fitta.entity.gym.QProgram.program;

@Repository
public class ProgramQueryRepository {
    private final JPAQueryFactory query;

    public ProgramQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Program> joinGymByMember(Long...ids){
        return query
                .selectFrom(program)
                .where(program.id.in(ids))
                .fetch();
    }
}
