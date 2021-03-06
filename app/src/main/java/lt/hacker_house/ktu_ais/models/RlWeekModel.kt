package lt.hacker_house.ktu_ais.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.RealmClass

/**
 * Created by simonas on 5/1/17.
 */

@RealmClass
open class RlWeekModel : RealmObject() {

    open var weekNumbersString: String? = null
        set(value) {
            field = value
            weekNumbers = parseWeekString(value!!)
        }

    open var grades: RealmList<RlGradeModel> = RealmList()

    @Ignore
    var weekNumbers: List<Int> = listOf()

    private fun parseWeekString(weekString: String): List<Int> {
        val intList = mutableListOf<Int>()
        weekString.split("-").forEach {
            intList.add(it.toInt())
        }
        return intList
    }
}