package com.mwg.bombapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mwg.bombapp.bean.Person;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Bmob.initialize(this, "25416a821a368a019091dbe71d723257");
    }

    //测试添加数据
    @OnClick(R.id.btn_add)
    public void add(View view) {
        Person p1 = new Person();
        p1.setName("ZYF");
        p1.setAddress("北京大兴");
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this,
                            "添加数据成功,返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "创建数据失败：", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //查找Person表里面id为32ba020fca的数据
    @OnClick(R.id.btn_query)
    public void query(View view) {
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("32ba020fca", new QueryListener<Person>() {
            @Override
            public void done(Person object, BmobException e) {
                if (e == null) {
                    Toast.makeText(MainActivity.this,
                            "查询成功:" + object.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "查询失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //更新Person表里面id为32ba020fca的数据，address内容更新为“北京朝阳”
    @OnClick(R.id.btn_modify)
    public void modify(View view) {
        final Person p2 = new Person();
        p2.setAddress("北京朝阳");
        p2.update("32ba020fca", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,
                            "更新成功:" + p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "更新失敗:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //删除一行id为4cb1495022的数据
    @OnClick(R.id.btn_delete)
    public void delete(){
        final Person p3 = new Person();
        p3.setObjectId("4cb1495022");
        p3.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,
                            "删除成功:" + p3.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,
                            "删除失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

}
