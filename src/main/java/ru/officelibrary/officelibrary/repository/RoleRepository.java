package ru.officelibrary.officelibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.officelibrary.officelibrary.entity.Role;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "SELECT r FROM Role r WHERE r.id IN :ids")
    List<Role> findRoleByIdList(@Param("ids") Collection<Long> ids);

    @Query(value = "SELECT r FROM Role r WHERE LOWER(r.name)  = LOWER(:roleName)")
    Role findRoleByRoleName(@Param("roleName") String roleName);
}

