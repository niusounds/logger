package com.orhanobut.logger

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

import com.google.common.truth.Truth.assertThat
import com.orhanobut.logger.Logger.DEBUG

@RunWith(RobolectricTestRunner::class)
class LogcatLogStrategyTest {

  @Test fun log() {
    val logStrategy = LogcatLogStrategy()

    logStrategy.log(DEBUG, "tag", "message")

    val logItems = ShadowLog.getLogs()
    val logItem = logItems.find { it.tag != "MonitoringInstr" }
    assertThat(logItem?.type).isEqualTo(DEBUG)
    assertThat(logItem?.msg).isEqualTo("message")
    assertThat(logItem?.tag).isEqualTo("tag")
  }

  @Test fun logWithNullTag() {
    val logStrategy = LogcatLogStrategy()

    logStrategy.log(DEBUG, null, "message")

    val logItems = ShadowLog.getLogs()
    val logItem = logItems.find { it.tag != "MonitoringInstr" }
    assertThat(logItem?.tag).isEqualTo(LogcatLogStrategy.DEFAULT_TAG)
  }

}
