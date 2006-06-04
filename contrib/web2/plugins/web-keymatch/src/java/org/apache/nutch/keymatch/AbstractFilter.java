/*
 * Copyright 2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nutch.keymatch;

import java.util.List;
import java.util.Map;

public abstract class AbstractFilter implements KeyMatchFilter {

  KeyMatchFilter next=null;
  
  /* (non-Javadoc)
   * @see org.apache.nutch.keymatch.IKeyMatchFilter#setNext(org.apache.nutch.keymatch.IKeyMatchFilter)
   */
  public void setNext(KeyMatchFilter next){
    this.next=next;
  }

  
  /* (non-Javadoc)
   * @see org.apache.nutch.keymatch.IKeyMatchFilter#filter(java.util.List, java.util.Map)
   */
  public KeyMatch[] filter(List matches, Map context) {
    if (next==null) {
      
      //update view counts
      for(int i=0;i<matches.size();i++){
        ((KeyMatch)matches.get(i)).viewCount++;
      }
      
      return (KeyMatch[])matches.toArray(new KeyMatch[matches.size()]);
    } else {
      return next.filter(matches, context);
    }
  }
}
