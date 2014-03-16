<div class="basic-info right-content">
    <h3>基本信息</h3>

    <form action="/secure/detailinfo" method="post">
        <div class="real-name">
            <label for="realName">真实姓名：</label>
            <input type="text" name="realName" id="realName"/>
        </div>
        <div class="date-of-birth">
            <label>出生年月：</label>
            <select name="province" id="province">
                <option value>请选择省份</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
            <select name="city" id="city">
                <option value>请选择城市</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
            <select name="prefecture" id="prefecture">
                <option value>请选择县区</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
        </div>
        <div class="id">
            <label>证件号码：</label>
            <select name="prefecture" id="one">
                <option value>请选择县区</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
            <input type="text"/>
        </div>
        <div class="location">
            <label>所在地：</label>
            <select name="prefecture" id="two">
                <option value>请选择县区</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
            <select name="prefecture" id="three">
                <option value>请选择县区</option>
                <option value="四川">四川</option>
                <option value="四川">新疆</option>
                <option value="四川">重庆</option>
                <option value="四川">河南</option>
            </select>
        </div>
        <button type="submit">保存</button>
    </form>
</div>