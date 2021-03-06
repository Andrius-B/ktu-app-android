package lt.hacker_house.ktu_ais.models

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import lt.welovedotnot.ktu_ais_api.models.YearModel

/**
 * Created by simonas on 4/30/17.
 */

@RealmClass
open class RlYearModel : RealmObject() {

    open var id: String? = null
    open var year: String? = null

    companion object {

        fun from(model: YearModel): RlYearModel {
            return RlYearModel().apply {
                id = model.id
                year = model.year
            }
        }

        fun from(modelList: List<YearModel>): List<RlYearModel> {
            return mutableListOf<RlYearModel>().apply {
                modelList.forEach { add(RlYearModel.from(it))}
            }
        }

    }
}