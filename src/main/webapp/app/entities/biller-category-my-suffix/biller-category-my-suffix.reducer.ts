import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBillerCategoryMySuffix, defaultValue } from 'app/shared/model/biller-category-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_BILLERCATEGORY_LIST: 'billerCategory/FETCH_BILLERCATEGORY_LIST',
  FETCH_BILLERCATEGORY: 'billerCategory/FETCH_BILLERCATEGORY',
  CREATE_BILLERCATEGORY: 'billerCategory/CREATE_BILLERCATEGORY',
  UPDATE_BILLERCATEGORY: 'billerCategory/UPDATE_BILLERCATEGORY',
  DELETE_BILLERCATEGORY: 'billerCategory/DELETE_BILLERCATEGORY',
  RESET: 'billerCategory/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBillerCategoryMySuffix>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BillerCategoryMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: BillerCategoryMySuffixState = initialState, action): BillerCategoryMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BILLERCATEGORY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BILLERCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BILLERCATEGORY):
    case REQUEST(ACTION_TYPES.UPDATE_BILLERCATEGORY):
    case REQUEST(ACTION_TYPES.DELETE_BILLERCATEGORY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BILLERCATEGORY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BILLERCATEGORY):
    case FAILURE(ACTION_TYPES.CREATE_BILLERCATEGORY):
    case FAILURE(ACTION_TYPES.UPDATE_BILLERCATEGORY):
    case FAILURE(ACTION_TYPES.DELETE_BILLERCATEGORY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLERCATEGORY_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_BILLERCATEGORY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BILLERCATEGORY):
    case SUCCESS(ACTION_TYPES.UPDATE_BILLERCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BILLERCATEGORY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/biller-categories';

// Actions

export const getEntities: ICrudGetAllAction<IBillerCategoryMySuffix> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BILLERCATEGORY_LIST,
    payload: axios.get<IBillerCategoryMySuffix>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBillerCategoryMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BILLERCATEGORY,
    payload: axios.get<IBillerCategoryMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBillerCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BILLERCATEGORY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IBillerCategoryMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BILLERCATEGORY,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBillerCategoryMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BILLERCATEGORY,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
