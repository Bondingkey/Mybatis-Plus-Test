package com.gzc.mp;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzc.mp.mapper.UserMapper;
import com.gzc.mp.pojo.User;
import com.gzc.mp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class MybatisPlusTestApplicationTests {

    @Autowired
    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 使用mp步骤
     * 1,导入maven依赖:mybatis-plus-boot-starter
     * 2,在启动类上添加@MapperScan("com.gzc.mp.mapper"),并写明要扫描的mapper接口的包
     * 3,在mapper接口上实现BaseMapper<POJO>
     * 4,要求POJO和数据库的一致性
     * 5,测试使用
     */
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    //测试mp自动生成的Service中的方法
    /*
    步骤
    1,新建一个Service接口继承IService<POJO>接口
    2,新建一个实现类实现刚才的接口同时继承ServiceImpl<POJOMapper,POJO>类
    3,如果想要自定义方法,自定义即可,注意ServiceImpl已经写好了自动注入的对象
     */
    @Test
    public void testService(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("age",18);
        User one = userService.getOne(objectQueryWrapper);
        System.out.println("one = " + one);

    }

    //增:默认情况下,mp会通过雪花算法生成一个19位的数值类型的递增的唯一标识符作为id值
    //且自动映射到id属性上,如果属性名为uid或者其他则失效
    /*
      mp的主键生成策略:使用type来指定策略
        AUTO(0):使用数据表的自动递增主键,要求:表中该字段必须是数值字段,且数据库中本身就设置递增属性
        NONE(1):默认的:生成19位唯一标识符作为id
        INPUT(2):要求用户自己输入
        ASSIGN_ID(3):如果用户自己输入了id,就用用户输入的,如果没有指定,就用19位唯一标识
        ASSIGN_UUID(4):如果用户自己输入了UUID,就用UUID,如果用户没有输入,就用mp自动生成的UUID
     */
    @Test
    public void testAdd(){
        //生成UUID
        String uuid = UUID.randomUUID().toString().replace("-","");
        userMapper.insert(new User(null,"管志成",23,"24092@qq.com","唱歌"));
    }

    //删
    @Test
    public void testDelete(){
        //根据id删
        //userMapper.deleteById(1728340478652325889L);

        //根据一系列id删
        //userMapper.deleteBatchIds(Arrays.asList(6,7,8,9));

        //根据Map删除
        //Map<String, Object> stringObjectHashMap = new HashMap<>();
        //stringObjectHashMap.put("name","Tom");//map中放入字段名-数据的键值对,设置多个参数为AND关系
        //userMapper.deleteByMap(stringObjectHashMap);

        //逻辑删除
        /*
        步骤
        1,表中添加is_deleted字段为tinyint类型,且自动填充0,pojo类中添加对应属性
        2,在pojo类属性中添加注解@TableLogic
         */
        userMapper.deleteById(16L);
    }

    //改
    @Test
    public void testUpdate(){
        User user = new User(1728335434020311042l, "jacklove2", 23, null, null);
        userMapper.updateById(user);
    }
    //自动填充新建更新时间
    @Test
    public void testTime(){
        //以前的新建更新时间方式
        User user = new User();
        user.setAge(23);
        user.setEmail("5555");
        user.setUsername("guanzhcheng");
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
        user.setAiHao("jjj");
        userMapper.insert(user);
        //mp自带的自动填充方式
        /*
        使用mp的自动填充步骤
        1,给要自动填充的字段所对应的属性上面添加@TableField()注解,fill = FieldFill.INSERT指定填充时机
        2,设置一个类,实现MetaObjectHandler类中的两个时机方法,指明填充的值,加上@Component放入容器
         */
    }
    //乐观锁
    /*
    乐观锁实现步骤
    1,给表和pojo类添加version属性
    2,给pojo类的version属性添加自动填充,添加时填充1
    3,给version属性添加@Version注解
    4,创建一个配置类编写一个乐观锁插件
    5,先查询后修改
     */
    @Test
    public void testLock(){
        User user = userMapper.selectById(1728572025158619137L);
        user.setUsername("你好啊人类");
        userMapper.updateById(user);
    }

    //查
    @Test
    public void testSelect(){
//        //根据一系列id查询列表
//        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5, 6));
//        for (User user : users) {
//            System.out.println("user = " + user);
//        }

//        //以map查询
//        Map<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("name","管志成");//map中放入字段名-数据的键值对,设置多个参数为AND关系
//        userMapper.selectByMap(stringObjectHashMap);

//        //分页查询
//        /*
//        分页查询步骤
//        1,在配置类中提供分页插件
//        2,创建Page类,通过有参构造器指定分页数据
//         */
//        Page<User> page = new Page<>(1,3);
//        Page<User> userPage = userMapper.selectPage(page, null);
//        System.out.println("总记录数 = " + userPage.getTotal());
//        System.out.println("总页数 = " + userPage.getPages());
//        System.out.println("当前页有几项 = " + userPage.getSize());
//        System.out.println("当前页是第几页 = " + userPage.getCurrent());
//        System.out.println("当前页对应的列表数据 = ");
//        List<User> records = userPage.getRecords();
//        for (User record : records) {
//            System.out.println(record);
//        }
//        System.out.println(" = " + userPage.getOrders());
//        System.out.println("是否有上一页 = " + userPage.hasPrevious());
//        System.out.println("是否有下一页 = " + userPage.hasNext());

///////////////////////使用Wrapper来查询///////////////////////////////////////////////
        QueryWrapper<User> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("age",18);//等于

//        queryWrapper.ne("age",18);//不等于

//        Map<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("name","管志成");
//        stringObjectHashMap.put("age","23");
//        queryWrapper.allEq(stringObjectHashMap);//所有的都相等,需要传map,在map中指定

//        queryWrapper.isNull("version");//查询为空的字段

//        queryWrapper.likeRight("name","J");//模糊查询,like、notLike、likeLeft、likeRight

//        queryWrapper.inSql("id","select id from db_user where id < 23");

//        queryWrapper.eq("age",23);

//        queryWrapper.select("name");

//        queryWrapper.eq("age","18").or().eq("name","Jone");

        queryWrapper.lt("age",50);//查询年龄小于50岁的
        queryWrapper.orderByDesc("age","id");//降序,先按第一个,相同再按第二个


        List<User> users = userMapper.selectList(queryWrapper);//当查询结果多条时使用selectOne,会报错
        for (User user : users) {
            System.out.println("user = " + user);
        }
        //使用Wrapper来统计
//        Integer integer = userMapper.selectCount(queryWrapper);
//        System.out.println("integer = " + integer);
    }

    @Test
    public void testUpdateWrapper(){
///////////////////////////////////////使用Wrapper来修改////////////////////////////////////
        UpdateWrapper<User> updateWrapper = new UpdateWrapper();

        updateWrapper.eq("age",23);
        updateWrapper.set("name","你好啊");

        int update = userMapper.update(new User(),updateWrapper);
        System.out.println("update = " + update);
    }


}
