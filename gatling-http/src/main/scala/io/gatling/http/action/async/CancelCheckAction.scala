/**
 * Copyright 2011-2017 GatlingCorp (http://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.action.async

import io.gatling.commons.validation.Validation
import io.gatling.core.action.{ Action, ActorBasedAction, RequestAction }
import io.gatling.core.session._
import io.gatling.core.stats.StatsEngine

abstract class CancelCheckAction(
    val requestName: Expression[String],
    actorName:       String,
    val statsEngine: StatsEngine,
    val next:        Action
) extends RequestAction with ActorBasedAction {

  def sendRequest(requestName: String, session: Session): Validation[Unit] =
    for (actor <- fetchActor(actorName, session)) yield actor ! CancelCheck(requestName, next, session)
}
