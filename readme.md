# WanAndroid  

### 网络请求：

使用HttpURLConnection发送网络请求，并且创建了一个回调接口HttpCallbackListener。创建一个Httputil工具类用于实现多种网络请求的发送，并在每个发送请求的方法中传入一个回调接口，方便在请求结束后返回数据和对UI界面进行修改。



### Josn数据的解析：

使用Json类解析网络请求返回的数据，并且创建一个工具包，里面是Json解析数据的工具类。类中有解析各种格式的解析方法，在网络请求后调用Json解析方法，并把解析完成后的数据通过回调接口返回，再在UI中进行更新。



### 导航栏：

使用Navigetion类和BottomNavigationView进行导航栏的创建，每一页分别使用Fragment，这样可以方便用户进行界面和功能的切换。



### 数据显示：

采用了RecyclerView。首页的文章只需通过返回来的数据然后在回调接口的方法中切换回主线程，通过写好的Recyclerview布局显示出来。在体系这一栏目中，每一个大标题里面的子项分类就是一个RecyclerView，然后页面的整体又是一个RecyclerView布局。具体实现方法就是在大的RecyclerView的Adapter里面的 onBindViewHolder方法中再创建一个Recyclerview，这样就能实现体系布局。每个体系的子项点进去的文章就是和首页文章一样的RecyclerView布局。

