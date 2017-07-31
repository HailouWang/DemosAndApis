
package hailou.demosandapis;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = getIntent();
        String path = intent.getStringExtra(Constant.DemosForApiPath);

        if (path == null) {
            path = "";
        }

        ListView listView = (ListView) findViewById(android.R.id.list);
        if(listView!=null){
            listView.setAdapter(new SimpleAdapter(this, getData(path),
                    android.R.layout.simple_list_item_1, new String[]{
                    "title"
            },
                    new int[]{
                            android.R.id.text1
                    }));
            listView.setOnItemClickListener(this);
            listView.setTextFilterEnabled(true);
        }

    }

    private List<Map<String, Object>> getData(String prefix) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        // 1、拿到加载到的所有数据
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Constant.CategoryForDemosApi);
        List<ResolveInfo> listActivities = getPackageManager().queryIntentActivities(intent, 0);
        if (listActivities == null || listActivities.size() == 0) {
            return data;
        }
        int currentIndex = 0;
        String prefexWithSlash = prefix;
        if (prefix == null || "".equals(prefix)) {
            prefix = "";
        } else {
            currentIndex = prefix.split("/").length;
            prefexWithSlash = prefix + "/";
        }
        // 2、遍历listActivities列表
        Map<String, Object> itemMap = new HashMap<String, Object>();
        for (ResolveInfo info : listActivities) {
            if (info == null) {
                continue;
            }

            // 3、得到activity的label
            CharSequence activityName = info.loadLabel(getPackageManager());
            String label = activityName != null ? activityName.toString() : info.activityInfo.name;
            if (label == null) {
                label = "";
            }

            // 4、合法数据，要么prefix为“”，或者label以prefix为前缀
            if ("".equals(prefix) || label.startsWith(/*prefix*/prefexWithSlash)) {
                // 5、获得即将要展示的Label
                String[] labelArray = label.split("/");
                String nextLabel = labelArray[currentIndex];
                // 6、是否遍历浏览:当prefi按照“/”拆分长度与label的拆分长度一致，则直接跳转到目标Intent
                if (currentIndex == labelArray.length - 1) {
                    // 7、将当前要展示的列表Label以及Intent，保存在Map集合中
                    addItem(data, nextLabel, getTargetIntent(info));
                } else {
                    String nextKeywords = "".equals(prefix) ? nextLabel : prefix + "/" + nextLabel;
                    if (itemMap.get(nextKeywords) == null) {
                        itemMap.put(nextKeywords, true);
                        addItem(data, nextLabel, getBroswerIntent(nextKeywords));
                    }
                }
            }
        }

        Collections.sort(data, sDisplayNameComparator);

        return data;
    }

    private void addItem(List<Map<String, Object>> listData, String title, Intent intent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", title);
        map.put("intent", intent);
        // 8、将itemMap数据条目，加入List<Map<String,Object>>中
        listData.add(map);
    }

    private Intent getBroswerIntent(String prefix) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra(Constant.DemosForApiPath, prefix);
        return intent;
    }

    private Intent getTargetIntent(ResolveInfo info) {
        Intent intent = new Intent();
        intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
        return intent;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    @Override
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);
        Intent intent = new Intent((Intent) map.get("intent"));
        intent.addCategory(Constant.CategoryForDemosApi);
        startActivity(intent);
        if (Constant.isFinishActivityTask)
            finish();
    }
}
