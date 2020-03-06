package test.provider.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
  Integer insertTest(@Param("name") String name);
}
