package net.dgsr.controller;

import io.swagger.annotations.*;
import net.dgsr.comment.ServiceResponse;
import net.dgsr.model.Tag;
import net.dgsr.service.TagService;
import net.dgsr.service.WXService;
import net.dgsr.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value="/tag" ,description="标签管理")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private WXService wxService;

    @Autowired
    private TagService tagService;

    @ApiOperation("从企业微信获取标签添加数据库")
    @RequestMapping(value="/addTagByWX",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> addTagByWX(){

        //获取微信标签集合
        List<Map<String,Object>> mapList = (List<Map<String,Object>>) wxService.getTagList().getData();

        //循环放入到数据库
        for (Map<String,Object>  map : mapList) {
            try {
                //将map转为对象
                Tag tag = (Tag) Utils.mapToObject(map,Tag.class);

                //判断添加状态
                int rowCount = tagService.addTagByWX(tag);
                if ( rowCount <= 0 ){
                    return ServiceResponse.createBySuccessMessage("添加失败！");
                }
            }catch ( Exception e){
                //手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                e.printStackTrace();
            }
        }
        return ServiceResponse.createBySuccessMessage("添加成功！");
    }

    @ApiOperation("添加标签")
    @ApiImplicitParam(paramType="query", name="name", value="标签名", dataType="String")
    @RequestMapping(value="/addTag",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> addTag(@RequestParam String name){
        return tagService.addTag( name );
    }


    @ApiOperation("查询标签")
    @RequestMapping(value="/selectTag",method = {RequestMethod.GET, RequestMethod.POST})
    public ServiceResponse<?> selectTag(@ModelAttribute Tag tag){
        return tagService.selectTag( tag );
    }


}
