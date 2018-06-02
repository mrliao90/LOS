package cn.los.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.los.common.util.PageUtil;
import cn.los.common.util.ResultUtil;
import cn.los.common.vo.PageVo;
import cn.los.common.vo.Result;

public abstract class BaseController<E,ID extends Serializable> {
    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract BaseService<E,ID> getService();

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result<E> get(@PathVariable ID id){

        E entity = getService().get(id);
        return new ResultUtil<E>().setData(entity);
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<E>> getAll(){

        List<E> list = getService().getAll();
        return new ResultUtil<List<E>>().setData(list);
    }

    @RequestMapping(value = "list-page",method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<E>> getByPage(@ModelAttribute PageVo page){

        Page<E> list = getService().findAll(PageUtil.initPage(page));
        return new ResultUtil<Page<E>>().setData(list);
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public Result<E> save(@ModelAttribute E entity){

        E e = getService().save(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Result<E> update(@ModelAttribute E entity){

        E e = getService().update(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Object> delAll(@RequestBody List<E> entities){

        getService().delete(entities);
        return new ResultUtil<Object>().setSuccessMsg("批量删除数据成功");
    }

    @RequestMapping(value = "delete-ids",method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Object> delAllByIds(@RequestParam ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }
}