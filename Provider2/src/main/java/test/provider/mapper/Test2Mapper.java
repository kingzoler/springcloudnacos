package test.provider.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Test2Mapper {
  Integer insertTest2(@Param("name") String name);
}
